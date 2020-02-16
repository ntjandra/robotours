from flask import jsonify
import random
import requests, logging
from robotours import app
from amadeus import Client, ResponseError

# Amadeus API Requirements
# Using "throwaway" key for testing purposes, it's fine to have this public.
amadeus = Client(
    client_id='5E7BaLL3RjaHbAPgGCvQWk9iGa9pMdZJ',
    client_secret='MiBY6N6jH3TojvkF'
)
'''
try:
    response = amadeus.reference_data.urls.checkin_links.get(airlineCode='BA')
    print(response.data)

except ResponseError as error:
    print(error)
'''
# amadeus = Client() # Requires setup for enviornment variables

@app.route('/location/interest/<int:lat>/<int:long>/<string:filter>', methods=['GET'])
def pick_poi(lat, long, filter='Any'):
    # Testing POI
    # Replace hardcode with varibles, when done.
    response = amadeus.reference_data.locations.points_of_interest.get(latitude=41.397158, longitude=2.160873)
    '''
    logging.basicConfig(filename='app.log', filemode='w', format='%(name)s - %(levelname)s - %(message)s')
    logging.debug("BODY")
    logging.debug(response.body)
    logging.debug("Data")
    logging.debug(response.data)
    logging.debug("Result")
    logging.debug(response.result)
    '''
    json_response = jsonify(response.data)

    # Parse the result into a list
    list_response = response.data

    # Filter Location results by Category
    # Assumption: There are only two categories.
    attractions = []
    restaurants = []

    for location in list_response:
        print(location['category'])
        # print(type(location['category']))
        if location['category'] == "SIGHTS":
            # print("Found an attraction!")
            attractions.append(location)
        else:
            restaurants.append(location)

    # Based on the filter, return
    if filter == 'tour':
        return jsonify(attractions)
    elif filter == 'food':
        return jsonify(restaurants)
    else:
        return json_response
    list = response.result
    location = random.choice(list)
    return jsonify(location)

if __name__ == '__main__':
    app.run()