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
		


def qfx():
	url_to_scrape="http://www.qfxcinemas.com/"
	soup=BeautifulSoup(requests.get(url_to_scrape).text,"html.parser")
	now=soup.find('div',class_="movies")
	showList=[]	
	for movie in now.find_all('div', class_="movie"):
		name=movie.find('h4',class_="movie-title").text
		category=movie.find('p',class_="movie-type").text
		img='http://www.qfxcinemas.com/'+ movie.find('img', class_="img-b").get('src')
		
		links=movie.find('div',class_="movie-poster").find('a', class_="ticket")
	
		new_url='http://www.qfxcinemas.com' + links.get('href')
		scrape=BeautifulSoup(requests.get(new_url).text,"html.parser")
	
		duration=[]
		for finding in scrape.find('div',class_='clearfix').find('div', class_='col-md-5').find_all('p'):
			duration.append(finding.text)


		length=duration[1].split(':')
		for timmings in scrape.find('div', class_='show-movies').find_all('div', class_='show-movie'):
			hall=timmings.find('h2', class_='show-hall-name').text
			time=[]
			time_url=[]
			for t in timmings.find('div',class_="show-times").find_all('a',class_="time-mark"):
				time.append(t.find(class_="time").text)
				time_url.append(t.get('href'))


			tt=" ".join(time)
			tu=" ".join(time_url)
			print(name)
			print(category)
			print(length[1])
			print(hall)
			print(time)
			print(time_url)
			print(img)
			data=ShowTimming(str(name),str(category),str(length[1]),tt,str(hall),img)
			showList.append(data)

	return showList		



def bigMovies():
	url_to_scrape = 'http://ticket.bigmovies.com.np/'
	plain_html_text = requests.get(url_to_scrape)
	soup = BeautifulSoup(plain_html_text.text, "html.parser")

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





def main():
	showTimes=[]
	showBig=bigMovies()
	showTimes.extend(showBig)
	showQfx=qfx()
	showTimes.extend(showQfx)

	return showTimes