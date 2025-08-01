import { useRef, useState } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './page/Login'
import SignUp from './page/SignUp'

const router = createBrowserRouter([
  {path:'/login', element: <Login/> },
  {path:'/signUp', element: <SignUp/> },
])

function App() {
  return <RouterProvider router={router}></RouterProvider>
}

export default App
