import app from './server'
import './database'

app.listen(app.get('port'), '192.168.1.27', () => {
  console.log(`Port ${app.get('port')}`)
})
