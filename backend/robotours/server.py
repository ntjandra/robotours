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

# amadeus = Client() # Requires setup for enviornment variables

@app.route('/location/interest/<int:lat>/<int:long>/<string:filter>', methods=['GET'])
def pick_poi(lat, long, filter='Any'):
    # Testing POI
    # Replace hardcode with varibles, when done.
    response = amadeus.reference_data.locations.points_of_interest.get(latitude=41.397158, longitude=2.160873)

    # Parse the result into a list
    list_response = response.data

    # Filter Location results by Category
    # Assumption: There are only two categories.
    attractions = []
    restaurants = []

    for location in list_response:
        if location['category'] == "SIGHTS":
            attractions.append(location)
        else:
            restaurants.append(location)

    # Based on the filter, return
    attractions = random.choice(attractions)
    restaurants = random.choice(restaurants)

    if filter == 'tour':
        if any(attractions):
            return jsonify(attractions)
        else:
            return "Error: No locations found"
    elif filter == 'food':
        if any(restaurants):
            return jsonify(restaurants)
        else:
            return "Error: No locations found"
    else:
        location = random.choice(list_response)
        return jsonify(location)
