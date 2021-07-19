import User from '../models/user'
import {signin, signup} from '../dao/user.dao'

export const signIn = (req, res) => {
  signin(req.body, (err, data) => {
    if (err) {
      if (err.kind === 'usuario no encontrado')
        res.status(404).send({
          message: `usuario con userName: ${req.body.userName} no encontrado`,
        })
      else
        res.status(500).send({
          message: `error al recibir el usuario con userName: ${req.body.userName}`,
        })
    } else
      res.send({
        status: 'correct',
        error: false,
        user: data,
        message: 'usuario encontrado',
      })
  })
}

export const signUp = (req, res) => {
  if (!req.body)
    res.status(400).send({
      message: 'contenido vacia',
    })

  const user = new User({
    userName: req.body.userName,
    email: req.body.email,
    password: req.body.password,
  })

  signup(user, (err, data) => {
    if (err)
      res.status(500).send({
        message: err.message || 'no se registro el usuario',
      })
    else
      res.send({
        status: 'success',
        error: false,
        user: null,
        message: 'usuario registrado correctamente',
      })
  })
}
