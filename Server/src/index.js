import app from './server'
import './database'

app.listen(app.get('port'), () => {
  console.log(`Port ${app.get('port')}`)
})
