import {Router} from 'express'
import {addComputer, getComputer, getAllComputers, editComputer, deleteOneComputer} from '../controllers/computer.controller'

const router = Router()

router.post('/api/computer/add', addComputer)

router.get('/api/computer/get/:id', getComputer)

router.get('/api/computer/getAll', getAllComputers)

router.put('/api/computer/update/:id', editComputer)

router.delete('/api/computer/delete/:id', deleteOneComputer)

export default router