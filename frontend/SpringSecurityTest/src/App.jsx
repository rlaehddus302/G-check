import { useRef, useState } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './page/Login'
import SignUp from './page/SignUp'
import NavBar from './component/NavBar'
import MainPage from './page/MainPage'
import {loginCheck} from './util/LoginCheck'

const router = createBrowserRouter([
  { path:'/', 
    element: <NavBar/>,
    loader : loginCheck,
    children : [
      {path:'/', element: <MainPage/>, loader : loginCheck }
    ],
  },
  {path:'login', element: <Login/> },
  {path:'signUp', element: <SignUp/> },
])

function App() {
  return <RouterProvider router={router}></RouterProvider>
}

export default App
