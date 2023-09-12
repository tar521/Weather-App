drop database if exists weather_db;
create database weather_db;
use weather_db;

# Dummy data for Locations
insert into location(name, region, country, lat, lon)
	values('London', 'City of London, Greater London', 'United Kingdom', 51.52, -0.11);
insert into location(name, region, country, lat, lon)
	values('Seattle', 'Washington', 'United States of America', 47.61, -122.33);
insert into location(name, region, country, lat, lon)
	values('Tokyo', 'Tokyo', 'Japan', 35.69, 139.69);