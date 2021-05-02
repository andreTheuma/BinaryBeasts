#python 3.8.5
import requests

#example api
result = requests.get(url="http://dummy.restapiexample.com/api/v1/employees")
jsondata = result.json()
print(jsondata['status'])
