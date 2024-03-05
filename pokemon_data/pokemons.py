from urllib.request import urlopen 
import json

base_url = 'https://pokeapi.co/api/v2/pokemon/'
base_url_description = 'https://pokeapi.co/api/v2/pokemon-species/' # For description in pokemon[flavor_text_entries][0][flavor_text]

pokemon_url_list = []
pokemon_url_description_list = []

for i in range(151):
  pokemon_url_list.append(base_url + str(i+1))
  pokemon_url_description_list.append(base_url_description + str(i+1))

class Pokemon:
  def __init__(self, name, types, description, height, weight):
    self.name = name  # name of the pokemon, in pokemon[forms][0][name]
    self.types = types  # list of types, in pokemon[types][0][type][name] and pokemon[types][1][type][name]
    self.description = description  # description of the pokemon, in pokemon[flavor_text_entries][0][flavor_text]
    self.height = height  # height of the pokemon, in pokemon[height]
    self.weight = weight  # weight of the pokemon, in pokemon[weight]

# Get the data from the APIs for each pokemon and its description and store it as Pokemon object in a list
pokemon_data_list = []    
  