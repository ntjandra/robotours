import requests

from amadeus import Client, ResponseError

# Amadeus API Requirements
# Using "throwaway" key for testing purposes, it's fine to have this public.
amadeus = Client(
    client_id='5E7BaLL3RjaHbAPgGCvQWk9iGa9pMdZJ',
    client_secret='MiBY6N6jH3TojvkF'
)

try:
    response = amadeus.reference_data.urls.checkin_links.get(airlineCode='BA')
    print(response.data)

except ResponseError as error:
    print(error)

# amadeus = Client() # Requires setup for enviornment variables

@app.route('/location/interest/<int:lat>/<int:long>')
def find_sighting(lat, long) {
    # Testing POI
    # Replace hardcode with varibles, when done.
    result = amadeus.reference_data.locations.points_of_interest.get(latitude=41.397158, longitude=2.160873)
    print(result)
    return result;
}

if __name__ == '__main__':
    app.run()