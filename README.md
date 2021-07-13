# elearning-service-sboot
This app would demonstrate how a "E-Learning webside APIs" would look like.


Design and Develop APIs for a e-learning application. Below contract you need to follow for the evaluation for the Social Feeds model in your application. Field names in the request object (which will be sent to your application), and field names in the response object (which will be received from your application) will be specified exactly as expected.

## **Fetch a Course info**

Get the course information for the given ID

*GET* /api/course/:courseId

Response Code 200 : Expected response should be of the form

```json
{
    "id": , ?------------ integer
    "title": "", ?------------ string 
    "teacher": "", ?------------ string
    "material": "", ?------------ string (url to the material)
    "total_students": ?------------ integer
}
```

Response Code 404 (failure) : Expected response should be of the form:

```json
{} ?--------- empty body
```

## **Create a Course**

Add a new Course to the platform

*POST* /api/course/:teacherId

The expected request should be of the form

```json
{
    "title": "", ?--------- string
    "material": "" ?--------- string (optional)
}
```

Response Code 200 : Expected response should be of the form containing following fields

```json
{
    "id": , ?------------ integer
    "title": "", ?------------ string 
    "teacher": "", ?------------ string
    "material": "", ?------------ string
    "total_students": ?------------ integer (0 by default)
}
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

## **Add material to the Course**

Add material to the Course

*POST* /api/course/:courseId/material

The expected request should be of the form

```json
{
    "material": "" ?--------- string (optional)
}
```

Response Code 200 : Expected response should be of the form containing following fields

```json
{
    "id": , ?------------ integer
    "material": "", ?------------ string 
    "course_id": ?------------ integer    
}
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

## **Delete a Course**

Delete a course from the platform

*DELETE* /api/course/:courseId


Response Code 200 : No Body


Response Code 404 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

## **Fetch a Teacher profile**

Get the teacher profile for the given ID

*GET* /api/teacher/:teacherId

Response Code 200 : Expected response should be of the form

```json
{
    "id": , ?------------ integer
    "first_name": "", ?------------ string 
    "last_name": "", ?------------ string
    "courses": ["A", "B"]?------------ string array of course title
}
```

Response Code 404 (failure) : Expected response should be of the form:

```json
{} ?--------- empty body
```

## **Create a Teacher profile**

Add a new Teacher profile to the platform

*POST* /api/teacher

The expected request should be of the form

```json
{
    "first_name": "", ?------------ string 
    "last_name": "" ?------------ string
}
```

Response Code 200 : Expected response should be of the form containing following fields

```json
{
    "id": , ?------------ integer
    "first_name": "", ?------------ string 
    "last_name": "", ?------------ string
    "courses": []?------------ string array of course title (empty by default)
}
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

## **Delete a Teacher profile**

Delete the teacher profile from the platform

*DELETE* /api/teacher/:teacherId


Response Code 200 : No Body


Response Code 404 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

## **Fetch a Student profile**

Get the student profile for the given ID

*GET* /api/student/:studentId

Response Code 200 : Expected response should be of the form

```json
{
    "id": , ?------------ integer
    "first_name": "", ?------------ string 
    "last_name": "", ?------------ string
    "birth_date": "", ?------------ string
    "gender": "M", ?------------ string
    "wants_newsletter: ?------------ boolean
    "courses": ["A", "B"]?------------ string array of course title
}
```

Response Code 404 (failure) : Expected response should be of the form:

```json
{} ?--------- empty body
```

## **Create a Student profile**

Add a new Student profile to the platform

*POST* /api/student

The expected request should be of the form

```json
{
    "first_name": "", ?------------ string 
    "last_name": "", ?------------ string
    "birth_date": "", ?------------ string
    "gender": "M", ?------------ string
    "wants_newsletter: ?------------ boolean
}
```

Response Code 200 : Expected response should be of the form containing following fields

```json
{
    "first_name": "", ?------------ string 
    "last_name": "", ?------------ string
    "birth_date": "", ?------------ string
    "gender": "M", ?------------ string
    "wants_newsletter: ?------------ boolean
    "courses": []?------------ string array of course title (empty by default)
}
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

## **Delete a Student profile**

Delete the teacher profile from the platform

*DELETE* /api/student/:studentId


Response Code 200 : No Body


Response Code 404 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

