#signup api (@postmapping)
http://localhost:8080/signup

#signin api (@postmapping)
http://localhost:8080/signin

#role based authentication (@getmapping api's)
http://localhost:8080/toolkit/manager
http://localhost:8080/toolkit/employee
http://localhost:8080/toolkit/employeeAndManager
http://localhost:8080/toolkit/scrumMaster
http://localhost:8080/toolkit/businessHead
http://localhost:8080/toolkit/anonymous

#get all employees
http://localhost:8080/toolkit/home

#get specific employee's details
http://localhost:8080/toolkit/home/{id}
(where id=e_id from employee table)

#add new employee
http://localhost:8080/toolkit/addEmp

#update existing employee details
http://localhost:8080/toolkit/updateEmp/{id}

#delete specific employee
http://localhost:8080/toolkit/deleteEmp/{id}

#get all skills
http://localhost:8080/toolkit/home2

#get specific employee's skills
http://localhost:8080/toolkit/home2/{id}

#add employee skills
http://localhost:8080/toolkit/addUserSkills

#update employee's skills
http://localhost:8080/toolkit/updateUserSkills/{id}

#delete employee's skills
http://localhost:8080/toolkit/deleteUserSkills/{id}

#get teams view
http://localhost:8080/toolkit/getTeams/{username}

#get teams skills
http://localhost:8080/toolkit/getTeamSkill/{username}

#get emp details of logged in user
http://localhost:8080/toolkit/getEmpDetails/{username}

#get skill details of logged in user
http://localhost:8080/toolkit/getSkillDetails/{username}


