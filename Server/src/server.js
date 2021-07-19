import express from 'express'
import constants from './constants/index'
import userRouter from './routes/user.routes'
import computerRoutes from './routes/computer.routes'

const app = express()

app.set('port', constants.PORT)

app.use(express.json())
app.use(express.urlencoded({extended: true}))

app.use(userRouter)
app.use(computerRoutes)

export default app