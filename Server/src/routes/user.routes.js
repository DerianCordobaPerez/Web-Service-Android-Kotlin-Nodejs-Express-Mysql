import {Router} from 'express'
import {signIn, signUp} from '../controllers/user.controller'

const router = Router()

router.post('/api/user/signin', signIn)

router.post('/api/user/signup', signUp)

export default router