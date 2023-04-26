const Sequelize = require('sequelize');

module.exports = class Level_1 extends Sequelize.Model{
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
            modelName: 'Level_1',
            tableName: 'level_1',
            paranoid: false,
            chatset: 'utf8'
        });
    }
}