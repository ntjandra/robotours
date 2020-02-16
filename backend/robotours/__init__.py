import os
from flask import Flask
'''
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from flask_login import LoginManager
from flask_mail import Mail
'''

app = Flask(__name__)
app.config['SECRET_KEY'] = 'ROBOT_KEY'
'''
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'
app.config['AMADEUS_KEY'] = 'API_TRAVEL_KEY' # Change this line in production. 
# Maybe store?
db = SQLAlchemy(app)
bcrypt = Bcrypt(app)
'''
# Set these from OS environment variables
from robotours import server