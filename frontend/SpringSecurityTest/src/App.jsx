import { useRef, useState } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './page/Login'
import SignUp from './page/SignUp'
import NavBar from './component/NavBar'
import MainPage from './page/MainPage'
import {loginCheck} from './util/LoginCheck'
import MyPage from './page/MyPage'
import { loader as myPageLoader } from './page/MyPage'
import GraduationRequirementsPage from './page/graduationRequirementsPage'
import { loader as gradRequirLoader } from './page/graduationRequirementsPage'
import CourseManagementPage from './page/CourseManagementPage'

const router = createBrowserRouter([
  { path:'/', 
    element: <NavBar/>,
    id: 'nav',
    loader : loginCheck,
    children : [
      {index:true, element: <MainPage/>},
      {path:'myPage', element: <MyPage/>, loader: myPageLoader},
      {path:'graduation-requirements', id: 'gradRequir', element: <GraduationRequirementsPage/>, loader: gradRequirLoader},
      {path:'course-management', element: <CourseManagementPage/>,},
    ],
  },
  {path:'login', element: <Login/> },
  {path:'signUp', element: <SignUp/> },
])

function App() {
  return <RouterProvider router={router}></RouterProvider>
}

export default App
