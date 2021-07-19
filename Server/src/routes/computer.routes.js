import {Router} from 'express'
import {addComputer, getAllComputers, editComputer, deleteOneComputer} from '../controllers/computer.controller'

const router = Router()

router.post('/api/computer/add', addComputer)

router.get('/api/computer/getAll', getAllComputers)

router.post('/api/computer/update', editComputer)

router.post('/api/computer/delete', deleteOneComputer)

export default router