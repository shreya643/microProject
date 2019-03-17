#!/usr/bin/python
import sqlite3
from bs4 import BeautifulSoup
import requests
import re
# from com.example.myapplication import ShowTimming


class ShowTimming():
	def __init__(self, movie, category,length,timming,hall,url):
    		self.movie = movie
    		self.category = category
    		self.length=length
    		self.timming=timming
    		self.hall=hall
    		self.url=url
		



# def add_showtime(conn,data):
# 		query='''INSERT INTO MOVIES(MOVIE, CATEGORY, MOVIE_LENGTH,TIMMINGS,HALL,IMAGE) VALUES(?,?,?,?,?,?);'''
# 		cur=conn.cursor()
# 		cur.execute(query,data)
# 		conn.commit()


def main():
	url_to_scrape = 'http://ticket.bigmovies.com.np/'
	plain_html_text = requests.get(url_to_scrape)
	soup = BeautifulSoup(plain_html_text.text, "html.parser")

		# t=soup.find('a', class_= 'property_title')
		# if t:
		# 	print(t.text)
	showList=[]	
	for now in soup.find("div",{"id":"NowShowingCarousel"}).find_all(class_="slide"):
		for title in now.find_all('div', class_= 'show-detail'):
			# Use all the SQL you like
			name=title.find('h3').text
			details=title.find(class_='show-info').find_all('p')
			print(name)
			a=details[0].text.split(',')
			category=" ".join(a)
		        # print(category)
			length=details[1].text 
	
					
		s=now.find("div",class_='show-times')
		timming=[]
		for time in s.find_all(class_="showtime-wrap"):
			if(time.find("span",class_="inactive")):
				t=time.find("span",class_="inactive")
				timming.append(t.text)
			else:
				t=time.find("span",class_="active")
				timming.append(t.text)

		tt="-".join(timming)
		img= "http://ticket.bigmovies.com.np"+ now.find("img").get('src');
	

		data=ShowTimming(str(name),str(category),str(length),str(tt),'Big Movies',img)
		showList.append(data)

	return showList
