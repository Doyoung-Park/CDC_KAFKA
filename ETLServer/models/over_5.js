const Sequelize = require('sequelize');

module.exports = class Over_5 extends Sequelize.Model{
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
            modelName: 'Over_5',
            tableName: 'over_5',
            paranoid: false,
            chatset: 'utf8'
        });
    }
}