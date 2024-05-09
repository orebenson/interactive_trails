#!/usr/bin/bash

# ensure jenkins knows where the selenium runner is
source /home/debian/.nvm/nvm.sh
export PATH="$PATH:/home/debian/.nvm/versions/node/v20.12.2/bin"

echo "."
echo "."
echo "RUNNING SELENIUM TESTS"

# move to jenkins workspace root
cd "/var/lib/jenkins/workspace/Smart Towns - Run JUnit + WebDriver + Checkstyle + Selenium Tests/Deployment/Selenium"
cd ../../

# start the app in the background and wait for launch - redirect output log and errors
screen -S "test_site" -d -m bash -c "java -jar build/libs/SmartTowns-0.0.1-SNAPSHOT.jar"
echo "Starting Selenium test site..."
sleep 8 # sleep to wait for server startup

# check that the test app is running
TEST_SITE=$(curl -s localhost:8080)
if [[ $TEST_SITE == *"VZTA"* ]]; then
    echo "Selenium test site running"
else
    echo "Selenium test site failed to run"
    echo "."
    echo "."
    exit 1
fi

# run the selenium test file
selenium-side-runner \
--output-directory=Deployment/Selenium/selenium_results \
--capabilities "goog:chromeOptions.args=[--headless,--nogpu,--no-sandbox,--disable-dev-shm-usage] browserName=chrome" \
--max-workers 1 \
--jest-options "\"jest --runInBand --detectOpenHandles --forceExit\"" \
./Deployment/Selenium/SmartTownsSeleniumTests.side

sleep 2
echo "Unformatted test results at Deployment/Selenium/selenium_results/*.json :"
jq -r '[
  "Total Tests: \(.numTotalTests)",
  "Passed Tests: \(.numPassedTests)"
] | .[]' Deployment/Selenium/selenium_results/*.json

# get and kill the process running the app after tests complete

# kill via PID
SPRING_SERVER_PID=$(ps -aux | grep "[S]martTowns-0.0.1-SNAPSHOT.jar" | grep -v grep | awk '{print $2}')
echo "Test server PID: $SPRING_SERVER_PID"
sudo kill "SPRING_SERVER_PID"

# kill via screen
screen -S "test_site" -X quit
echo "Selenium test site shut down"


echo "Converting json results to xml..."

# function to convert selenium json into junit xml as for some reason selenium cant do it anymore
json_to_xml() {
    local numRuntimeErrorTestSuites=$(jq '.numRuntimeErrorTestSuites' <<< "$1")
    local numPassedTests=$(jq '.numPassedTests' <<< "$1")
    local numTotalTests=$(jq '.numTotalTests' <<< "$1")
    local numFailedTests=$(jq '.numFailedTests' <<< "$1")
    local startTime=$(jq '.startTime' <<< "$1")
    local testResults=$(jq -c '.testResults[0].assertionResults[]' <<< "$1")

    local totalTime=$(($(date +%s%N) / 1000000 - $startTime))

    echo '<?xml version="1.0" encoding="UTF-8"?>'
    echo '<testsuite name="SeleniumTests" time="'$totalTime'" tests="'$numTotalTests'" failures="'$numFailedTests'" errors="'$numRuntimeErrorTestSuites'">'

    while IFS= read -r row; do
        local title=$(jq -r '.title' <<< "$row")
        local status=$(jq -r '.status' <<< "$row")
        local duration=$(jq -r '.duration' <<< "$row")

        echo '    <testcase name="'$title'" classname="SeleniumTests" duration="'$duration'">'
        if [ "$status" != "passed" ]; then
            echo '        <failure message="Test failed"/>'
        fi
        echo '    </testcase>'
    done <<< "$testResults"

    echo '</testsuite>'
}

formatted_xml=$(json_to_xml "$(cat Deployment/Selenium/selenium_results/*.json)")
output_file="Deployment/Selenium/selenium_results/formatted_results.xml"
echo $formatted_xml
echo $formatted_xml > $output_file


echo "Selenium tests complete. formatted test results at Deployment/Selenium/selenium_results/*.xml"
echo "."
echo "."
