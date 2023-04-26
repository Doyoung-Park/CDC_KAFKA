const Sequelize = require('sequelize');

module.exports = class Subscriber extends Sequelize.Model{
    static init(sequelize){
        return super.init({
            name:{
                type: Sequelize.TEXT

            },
            email:{
                type: Sequelize.TEXT
            },
            level:{
                type: Sequelize.INTEGER
            },
            password:{
                type: Sequelize.INTEGER
            },
        },{
            sequelize,
            timestamps : false,
            createdAt : false,
            updatedAt : false,
            underscored: false,
            modelName: 'Subscriber',
            tableName: 'subscriber',
            paranoid: false,
            chatset: 'utf8'
        });
    }
}