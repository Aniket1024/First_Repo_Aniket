Feature: Validate Playlist API's

Scenario: verify if create playlist is working
Given create playlist api payload 
When user calls with POST http request for create playlist
Then api call executed with status code 201

Scenario: verify if fetch playlist functionality is working
Given GET playlist api payload 
When user calls with GET http request
Then api call should executes with status code 200

Scenario: verify if update playlist functionality is working
Given update playlist api payload 
When user calls with PUT http request
Then api call executes with status code 200

Scenario: verify if update playlist functionality is working or not