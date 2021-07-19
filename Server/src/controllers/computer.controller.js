import Computer from '../models/computer'
import {
  createComputer,
  deleteComputer,
  getAll,
  getOne,
  updateComputer,
} from '../dao/computer.dao'

export const addComputer = (req, res) => {
  if (!req.body)
    res.status(400).send({
      message: 'contenido vacia',
    })

  const computer = new Computer({
    name: req.body.name,
    price: req.body.price,
    brand: req.body.brand,
    description: req.body.description,
  })

  createComputer(computer, (err, data) => {
    if (err)
      res.status(500).send({
        message: err.message || 'no se guardo la computadora',
      })
    else
      res.send({
        status: 'success',
        error: false,
        computers: null,
        message: 'computadora agregada correctamente',
      })
  })
}

export const editComputer = (req, res) => {
  if (!req.body)
    res.status(400).send({
      message: 'contenido vacia',
    })

  updateComputer(req.body.id, new Computer(req.body), (err, data) => {
    if (err) {
      if (err.kind === 'computadora no encontrada')
        res.status(404).send({
          message: `computadora con id: ${req.body.id} no encontrado`,
        })
      else
        res.status(500).send({
          message: `error al actualizar la computadora con id: ${req.body.id}`,
        })
    } else
      res.send({
        status: 'success',
        error: false,
        data: null,
        message: 'computadoras obtenidas con exito',
      })
  })
}

export const deleteOneComputer = (req, res) => {
  deleteComputer(req.body.id, (err, data) => {
    if (err) {
      if (err.kind === 'computadora no encontrada')
        res.status(404).send({
          message: `computadora con id: ${req.body.id} no encontrado`,
        })
      else
        res.status(500).send({
          message: `error al eliminar la computadora con id: ${req.body.id}`,
        })
    } else
      res.send({
        status: 'success',
        error: false,
        computers: null,
        message: 'computadora eliminada correctamente',
      })
  })
}

export const getComputer = (req, res) => {
  getOne(req.body.id, (err, data) => {
    if (err) {
      if (err.kind === 'computadora no encontrada')
        res.status(404).send({
          message: `computadora con id: ${req.body.id} no encontrado`,
        })
      else
        res.status(500).send({
          message: `error al recibir la computadora con id: ${req.body.id}`,
        })
    } else
      res.send({
        status: 'success',
        error: false,
        computers: null,
        message: 'computadora actualizada',
      })
  })
}

export const getAllComputers = (req, res) => {
  getAll((err, data) => {
    if (err)
      res.status(500).send({
        message: err.message || 'no se obtuvieron las computadoras',
      })
    else
      res.send({
        status: 'success',
        error: false,
        computers: data,
        message: 'computadoras obtenidas con exito',
      })
  })
}
