import cors from 'cors'
import express from 'express'
import constants from './constants/index'
import computerRoutes from './routes/computer.routes'

const app = express()

app.set('port', constants.PORT)

app.use(computerRoutes)

export default app