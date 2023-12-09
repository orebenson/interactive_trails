function initMap() {
    if (!window.google || !window.google.maps) {
        console.error('Google Maps API is not available.');
        return;
    }

    const mapElement = document.getElementById('map');
    const trailId = mapElement.dataset.trailId;

    const map = new google.maps.Map(mapElement, {
        zoom: 8,
        center: { lat: 0, lng: 0 }, // Center will be updated when we get the checkpoints
    });

    fetch(`/api/trails/${trailId}/checkpoints`)
        .then(response => response.json())
        .then(checkpoints => {
            const bounds = new google.maps.LatLngBounds();
            checkpoints.forEach(function(checkpoint) {
                // Assuming the first element is latitude and the second is longitude
                const position = new google.maps.LatLng(checkpoint.coordinates[0], checkpoint.coordinates[1]);
                bounds.extend(position);
                new google.maps.Marker({
                    position: position,
                    map: map,
                    title: checkpoint.name // Assuming there's a 'name' property
                });
            });

            // Adjust the map view to fit all the markers
            map.fitBounds(bounds);
        })
        .catch(error => console.error('Error fetching checkpoint data:', error));
}

// Make initMap globally available
window.initMap = initMap;
