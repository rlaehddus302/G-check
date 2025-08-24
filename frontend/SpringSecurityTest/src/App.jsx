import { useRef, useState } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './page/Login'
import SignUp from './page/SignUp'
import NavBar from './component/NavBar'
import MainPage from './page/MainPage'

const router = createBrowserRouter([
  { path:'/', 
    element: <NavBar/>, 
    children : [
      {path:'/', element: <MainPage/>, }
    ],
  },
  {path:'/login', element: <Login/> },
  {path:'/signUp', element: <SignUp/> },
])

function App() {
  return <RouterProvider router={router}></RouterProvider>
}

export default App
