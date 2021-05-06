#python 3.8.5
import requests

base_url = "http://127.0.0.1:8080/"

def fullPath(ending):
	return base_url + ending

def post_req(ending, data):
	headers = {
		"Content-type": "application/json",
		"Accept": "*/*"
	}
	result = requests.post(fullPath(ending), headers=headers, json=data)
	return result.json()

def get_req(ending):
	result = requests.get(url=fullPath(ending))
	return result.json()

main = """
1.	Manage clients in the system
2.	Manage bookings
3.	Manage resources and property
4.	Generate financial report
5.	Exit"""

def manageClients():
	menu = """
1. Add new client
2. Remove client"""
	print(menu)
	num = int(input(">> "))

def manageBookings():
	menu = """
1.	Schedlue new booking
2.	List all bookings
3.	Remove booking"""
	print(menu)
	
	def schedule():
		cid = input("client id >> ")
		rid = input("resouce id >> ")
		start = input("start time >> ")
		duration = input("duration >> ")
		data = {
			"clientId": cid,
			"length": duration,
			"resourceId": rid,
			"startTime": start
		}
		result = post_req("schedule", data)
		if "error" in result:
			print("Scheduling failed")
			print(result["message"])
		else:
			print("Success")
			print("Booking id: " + str(result["id"]))

	def listAll():
		result = get_req("allScheds")
		for booking in result:
			print(booking)
	
	def remove():
		bid = input("booking id >> ")
		print("deleting " + str(bid))
		#TODO: call delete api

	bookingFuncs = {
		1: schedule,
		2: listAll,
		3: remove
	}

	num = int(input(">> "))
	bookingFuncs[num]()

def manageResources():
	menu = """
1. Add new resource
2. Remove resource"""
	print(menu)
	num = int(input(">> "))

def financialReport():
	print("expenses: 500")
	print("revenue: 0")

mainFuncs = {
	1:	manageClients,
	2:	manageBookings,
	3:	manageResources,
	4:	financialReport
}

while True:
	print(main)
	num = int(input(">> "))
	if num == 5:
		print("Bye")
		break
	mainFuncs[num]()
