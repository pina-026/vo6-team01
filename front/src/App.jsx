import SignUp from './Login'
import SignIn from './signin'
import React from 'react'
import { BrowserRouter, Route, Link } from 'react-router-dom'

const PageSignup = () => {
  return (
    <div className="PageSignup">
      <SignUp />
    </div>
  )
}
const PageSignin = () => {
  return (
    <div className="PageSignin">
      <SignIn />
    </div>
  )
}

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Route path="/" exact component={PageSignup} />
          <Route path="/pageSignin" component={PageSignin} />
        </div>
      </BrowserRouter>
    </div>
  )
}

export default App
