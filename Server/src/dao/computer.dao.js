import database from '../database'
import constants from '../constants'

/**
 *
 * @param {String} err mensaje de error
 * @param {Function} result funcion de orden superior para manejar el resultado
 */
const error = (err, result) => {
  console.log(`error: ${err}`)
  result(err, null)
}

export const createComputer = (computer, result) => {
  database.query(
    `INSERT INTO ${constants.TABLE_NAME} (name, price, brand, description) VALUES (?, ?, ?, ?)`,
    [computer.name, computer.price, computer.brand, computer.description],
    (err, res) => {
      if (err) {
        error(err, result)
        return
      }

      console.log(`cliente creado`, {id: res.insertId, ...computer})
      result(null, {id: res.insertId, ...computer})
    },
  )
}

/**
 *
 * @param {Number} id identificador de la computadora
 * @param {*} result funcion de orden superior
 */
export const getOne = (id, result) => {
  database.query(
    `SELECT * FROM ${constants.TABLE_NAME} WHERE id = ${id}`,
    (err, res) => {
      if (err) {
        error(err, result)
        return
      }

      if (res.length) {
        console.log(`computadora encontrada: ${res[0]}`)
        result(null, res[0])
        return
      }

      result({kind: 'computadora no encontrada'}, null)
    },
  )
}

/**
 *
 * @param {*} result funcion de orden superior
 */
export const getAll = (result) => {
  database.query(`SELECT * FROM ${constants.TABLE_NAME}`, (err, res) => {
    if (err) {
      error(err, result)
      return
    }

    console.log(`computers: ${res}`)
    result(null, res)
  })
}

/**
 *
 * @param {Number} id identificador de la computadora
 * @param {*} result funcion de orden superior
 */
export const deleteComputer = (id, result) => {
  database.query(
    `DELETE FROM ${constants.TABLE_NAME} WHERE id = ${id}`,
    (err, res) => {
      if (err) {
        error(err, result)
        return
      }

      if (!res.affectedRows) {
        result({kind: 'computadora no encontrada'}, null)
        return
      }

      console.log('computadora borrada con exito')
      result(null, res)
    },
  )
}

/**
 *
 * @param {Number} id identificador de la computadora actualizar
 * @param {Object} computer computadora con los datos que usaremos para actualizar
 * @param {*} result funcion de orden superior
 */
export const updateComputer = (id, computer, result) => {
  database.query(
    `UPDATE ${constants.TABLE_NAME} SET name = ?, price = ?, brand = ?, description = ? WHERE id = ?`,
    [computer.name, computer.price, computer.brand, computer.description, id],
    (err, res) => {
      if (err) {
        error(err, result)
        return
      }

      if (!res.affectedRows) {
        result({kind: 'computadora no encontrada'}, null)
        return
      }

      console.log(`cliente actualizado`, {id, ...computer})
      result(null, {id, ...computer})
    },
  )
}
