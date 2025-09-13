import { useRef, useState } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './page/Login'
import SignUp from './page/SignUp'
import NavBar from './component/NavBar'
import MainPage from './page/MainPage'
import {loginCheck} from './util/LoginCheck'
import MyPage from './page/MyPage'

const router = createBrowserRouter([
  { path:'/', 
    element: <NavBar/>,
    id: 'nav',
    loader : loginCheck,
    children : [
      {index:true, element: <MainPage/>},
      {path:'myPage', element: <MyPage/>}
    ],
  },
  {path:'login', element: <Login/> },
  {path:'signUp', element: <SignUp/> },
])

function App() {
  return <RouterProvider router={router}></RouterProvider>
}

export default App
