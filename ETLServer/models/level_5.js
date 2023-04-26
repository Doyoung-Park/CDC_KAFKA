const Sequelize = require('sequelize');

module.exports = class Level_5 extends Sequelize.Model{
    static init(sequelize){
        return super.init({
            name:{
                type: Sequelize.TEXT
            },
            email:{
                type: Sequelize.TEXT
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
            modelName: 'Level_5',
            tableName: 'level_5',
            paranoid: false,
            chatset: 'utf8'
        });
    }
}