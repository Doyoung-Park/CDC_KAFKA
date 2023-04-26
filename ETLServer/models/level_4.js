const Sequelize = require('sequelize');

module.exports = class Level_4 extends Sequelize.Model{
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
            modelName: 'Level_4',
            tableName: 'level_4',
            paranoid: false,
            chatset: 'utf8'
        });
    }
}