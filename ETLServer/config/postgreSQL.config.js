require('dotenv').config();

module.exports = {
  "development": {
    "username": process.env.DEVELOPMENT_USER,
    "password": process.env.DEVELOPMENT_PASSWORD,
    "database": process.env.DEVELOPMENT_DATABASE,
    "host": process.env.DEVELOPMENT_HOST,
    "dialect": "postgres"
  },
  "test": {
    "username": "",
    "password": "",
    "database": "",
    "host": "",
    "dialect": "postgres"
  },
  "production": {
    "username": "",
    "password": "",
    "database": "",
    "host": "",
    "dialect": "postgres"
  }
}
