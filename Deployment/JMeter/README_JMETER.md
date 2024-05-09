# JMeter Performance tests

### Admin Test
- Tests all admin routes including login and add trails

### User Test
- Tests all user routes including registration, login, profile, and scan

### Command to run jmeter tests on localhost (set server_ip and output_file names):
<path-to>/jmeter -Jserver_ip=localhost -Joutput_file=results/results -n -t .\Server_Test_Plan.jmx -l results/tmp.log

### Command to run jmeter tests on localhost and generate report dashboard (local_tests/report cannot already exist):
<path-to>/jmeter -Jserver_ip=localhost -Joutput_file=local_tests/results -n -t .\Server_Test_Plan.jmx -e -l local_tests/tmp.log -olocal_tests/report