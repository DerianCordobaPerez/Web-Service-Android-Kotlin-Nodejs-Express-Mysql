import database from '../database'
import constants from '../constants'

const error = (err, result) => {
  console.log(`error: ${err}`)
  result(err, null)
}

export const signin = (user, result) => {
  database.query(
    `SELECT * FROM ${constants.TABLE_USER} WHERE userName = '${user.userName}' AND password = '${user.password}'`,
    (err, res) => {
      if (err) {
        error(err, result)
        return
      }

      if (res.length) {
        console.log(`usuario encontrado: ${res[0]}`)
        result(null, res[0])
        return
      }

      result({kind: 'usuario no encontrado'}, null)
    },
  )
}

export const signup = (user, result) => {
  database.query(
    `INSERT INTO ${constants.TABLE_USER} (userName, email, password) VALUES (?, ?, ?)`,
    [user.userName, user.email, user.password],
    (err, res) => {
      if (err) {
        error(err, result)
        return
      }

      console.log(`usurio creado: `, {id: res.insertId, ...user})
      result(null, {id: res.insertId, ...user})
    },
  )
}
