from urllib.request import urlopen 
import requests
import json

base_url = 'https://pokeapi.co/api/v2/pokemon/'
base_url_description = 'https://pokeapi.co/api/v2/pokemon-species/' # For description in pokemon[flavor_text_entries][0][flavor_text]

pokemon_url_list = []
pokemon_url_description_list = []

for i in range(151):
  pokemon_url_list.append(base_url + str(i+1))
  pokemon_url_description_list.append(base_url_description + str(i+1))

class Pokemon:
  def __init__(self, name, types, description, height, weight, image_url):
    self.name = name  # name of the pokemon, in pokemon[forms][0][name]
    self.types = types  # list of types, in pokemon[types][0][type][name] and pokemon[types][1][type][name]
    self.description = description  # description of the pokemon, in pokemon[flavor_text_entries][0][flavor_text]
    self.height = height  # height of the pokemon, in pokemon[height]
    self.weight = weight  # weight of the pokemon, in pokemon[weight]
    self.image_url = image_url  # image of the pokemon, in pokemon[sprites][other][official-artwork][front_default]
    
# Get the data from the APIs for each pokemon and its description and store it as Pokemon object in a list
pokemon_data_list = []    
  
for i in range(151):
  response = requests.get(pokemon_url_list[i])
  pokemon = response.json()
  response_description = requests.get(pokemon_url_description_list[i])
  pokemon_description = response_description.json()
  
  name = pokemon['forms'][0]['name']
  types = [pokemon['types'][0]['type']['name']]
  if len(pokemon['types']) > 1:
    types.append(pokemon['types'][1]['type']['name'])
  description = pokemon_description['flavor_text_entries'][0]['flavor_text']
  height = pokemon['height']
  weight = pokemon['weight']
  image_url = pokemon['sprites']['other']['official-artwork']['front_default']
  
  pokemon_data_list.append(Pokemon(name, types, description, height, weight, image_url))

# Next step: store in sql:
for i in range(151):
  print(pokemon_data_list[i].name)