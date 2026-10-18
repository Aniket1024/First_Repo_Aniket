Feature: Validate Search feature for song

Scenario: verify Search for song is working
Given GET Search song api payload is available
| songname | type | artist |
| Khudaya Ve | track | Salim Merchant |
When user calls with GET http request for Search for Item
Then api calls executed with status code 200

Scenario: verify Search for song is working or not
Given GET Search song api payload is available

Scenario: verify Search for song is working or not
Given GET Search song api payload is available