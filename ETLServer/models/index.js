const Sequelize = require('sequelize');
const env = process.env.NODE_ENV || 'development';

const config = require('../config/postgreSQL.config.js')[env];
const db = {};

const sequelize = new Sequelize({
    host: config.host,
    username: config.username,
    password: config.password,
    database: config.database,
    dialect: config.dialect
});
db.sequelize = sequelize;

const Subscriber = require('./subscriber');
const Level_1 = require('./level_1');
const Level_2 = require('./level_2');
const Level_3 = require('./level_3');
const Level_4 = require('./level_4');
const Level_5 = require('./level_5');
const Over_5 = require('./over_5');

db.Subscriber = Subscriber;
db.Level_1 = Level_1;
db.Level_2 = Level_2;
db.Level_3 = Level_3;
db.Level_4 = Level_4;
db.Level_5 = Level_5;
db.Over_5 = Over_5;

Subscriber.init(sequelize);
Level_1.init(sequelize);
Level_2.init(sequelize);
Level_3.init(sequelize);
Level_4.init(sequelize);
Level_5.init(sequelize);
Over_5.init(sequelize);

module.exports = db;