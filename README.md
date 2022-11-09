# REST API getgithubinfo

## Configuration

Before first run you need run generate authorisation token fallowing instruction here:
https://docs.github.com/en/enterprise-cloud@latest/authentication/authenticating-with-saml-single-sign-on/authorizing-a-personal-access-token-for-use-with-saml-single-sign-on

### application.yml

    github:
        logintoken: <GENERATED_AUTHORISATION_TOKEN>
        apiurl: https://api.github.com

# REST API

The REST API to the example app is described below.

## Get my repositories general information

### request

'POST /getMyRepoInfo/'

    curl -i -H 'Accept: application/json' http://localhost:8080/getMyRepoInfo

### response

    HTTP/1.1 200 OK
    Date: Tue, 08 Nov 2022 23:16:28 GMT
    Status: 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 1427
    [
    {
        "Repository Name": "getgithubinfo",
        "Repository URL": "https://github.com/slewdziu99/getgithubinfo",
        "Owner Login": "slewdziu99",
        "brunches": {
            "main": "2f7c1c3ac76bcaa8cf2b33d829314247a2fb9e2d"
        }
    },
    {
        "Repository Name": "guru-sprong5-recipe-app",
        "Repository URL": "https://github.com/slewdziu99/guru-sprong5-recipe-app",
        "Owner Login": "slewdziu99",
        "brunches": {
            "master": "f7058d6d86c37f5e84a6071ce5705b3fa59602a4"
        }
    },
    {
        "Repository Name": "jokeapp",
        "Repository URL": "https://github.com/slewdziu99/jokeapp",
        "Owner Login": "slewdziu99",
        "brunches": {
            "master": "99a02ae7f136d86486aec8111d148de47ff4dd21"
        }
    },
    {
        "Repository Name": "nauka",
        "Repository URL": "https://github.com/slewdziu99/nauka",
        "Owner Login": "slewdziu99",
        "brunches": {
            "master": "d952f249325e75280044e9ca8b6e6c26caf4fac0"
        }
    },
    {
        "Repository Name": "sfg-di",
        "Repository URL": "https://github.com/slewdziu99/sfg-di",
        "Owner Login": "slewdziu99",
        "brunches": {
            "dependabot/maven/junit-junit-4.13.1": "efd6877f26f210b225d5cbfa13f2466946c5421e",
            "master": "670b1089446e4b1edfaea63a28cd60c1e5fbfcd9"
        }
    },
    {
        "Repository Name": "sfg-pet-clinic",
        "Repository URL": "https://github.com/slewdziu99/sfg-pet-clinic",
        "Owner Login": "slewdziu99",
        "brunches": {
            "master": "00e83ef43299826afdf20d6c1f8717bf25096d0a"
        }
    },
    {
        "Repository Name": "spring5webapp",
        "Repository URL": "https://github.com/slewdziu99/spring5webapp",
        "Owner Login": "slewdziu99",
        "brunches": {
            "master": "d63c2d1099d3eebb79c416ef9d4266c5e002fd12"
        }
    }
    ]]

##  Get repositories general information for user

### request

'POST /getMyRepoInfo/'

    curl -i -H 'Accept: application/json' http://localhost:8080/getMyRepoInfo?user=slewdziu99

### response

    HTTP/1.1 200 OK
    Date: Tue, 08 Nov 2022 23:16:03 GMT
    Status: 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 409
    [
        {
            "Repository Name": "getgithubinfo",
            "Repository URL": "https://github.com/slewdziu99/getgithubinfo",
            "Owner Login": "slewdziu99",
            "brunches": {
            "main": "2f7c1c3ac76bcaa8cf2b33d829314247a2fb9e2d"
            }
        },
        {
            "Repository Name": "guru-sprong5-recipe-app",
            "Repository URL": "https://github.com/slewdziu99/guru-sprong5-recipe-app",
            "Owner Login": "slewdziu99",
            "brunches": {
            "master": "f7058d6d86c37f5e84a6071ce5705b3fa59602a4"
            }
        }
    ]





