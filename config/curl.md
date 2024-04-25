### curl samples (application deployed at application context `traderdiary`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/traderdiary/rest/admin/users`

#### get Users 100001
`curl -s http://localhost:8080/traderdiary/rest/admin/users/100001`

#### get All positions
`curl -s http://localhost:8080/traderdiary/rest/profile/positions`

#### get positions 100003
`curl -s http://localhost:8080/traderdiary/rest/profile/positions/100003`

#### filter positions
`curl -s "http://localhost:8080/traderdiary/rest/profile/positions/filter?startDate=2020-01-30&startTime=07:00:00&endDate=2020-01-31&endTime=11:00:00"`

#### get positions not found
`curl -s -v http://localhost:8080/traderdiary/rest/profile/positions/100008`

#### delete positions
`curl -s -X DELETE http://localhost:8080/traderdiary/rest/profile/positions/100002`

#### create positions
`curl -s -X POST -d '{"dateTime":"2020-02-01T12:00","description":"Created lunch","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/traderdiary/rest/profile/positions`

#### update positions
`curl -s -X PUT -d '{"dateTime":"2020-01-30T07:00", "description":"Updated breakfast", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/traderdiary/rest/profile/positions/100003`