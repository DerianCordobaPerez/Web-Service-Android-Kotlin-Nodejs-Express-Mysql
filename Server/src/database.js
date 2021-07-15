import Mysql from 'mysql2'
import constants from './constants'

const connection = Mysql.createConnection({
    host: constants.HOST,
    database: constants.DATABASE,
    user: constants.USERNAME,
    password: constants.PASSWORD
})

connection.connect(err => {
    if(err) throw err
    console.log(`Db Connected`)   
})

export default connection