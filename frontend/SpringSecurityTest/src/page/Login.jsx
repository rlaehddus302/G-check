import { useRef, useState } from 'react'
import classes from './Login.module.css'
import { Link, useNavigate } from 'react-router-dom'
export default function Login() {

  const id = useRef()
  const password = useRef()
  const navigate = useNavigate();
  async function login(event) 
  {
    event.preventDefault()
    let data = {
      "id" : id.current.value,
      "password" : password.current.value,
    }
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body : JSON.stringify(data),
        credentials: 'include',
      });
      if (!response.ok) {
        console.log(response);
        throw new Error('로그인 실패');
      }
      else
      {
        let data = await response.json();
        console.log(data)
        navigate("/")
      }
    } catch (error) {
      console.error('에러:', error);
    }
  }

  return (
    <>
      <section className='d-flex align-items-center justify-content-center vw-100 vh-100 bg-body-tertiary'>
        <div>
          <div className='d-flex align-items-center justify-content-center'>
            <div className={`d-flex align-items-center justify-content-center bg-gradient-custom rounded-4 ${classes.gradient} me-2`} style={{width: "4.0rem", height: "4.0rem"}}>
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" className="bi bi-mortarboard text-white" viewBox="0 0 16 16">
                    <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917zM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
                    <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46z"/>
                </svg>
            </div>
            <span className={classes.title}>G-Check</span>
          </div>
          <p className={classes.paragraph}>복잡한 졸업관리를 쉽게, G-check</p>
          <form onSubmit={login}>
            <input ref={id} type="text" id='id' className={`border border-secondary-subtle p-3 rounded-3 ${classes.input}`} placeholder="아이디"/>
            <input ref={password} type="password" id='password' className={`border border-secondary-subtle p-3 rounded-3 ${classes.input}`} placeholder='비밀번호' />
            <button className="w-100 btn btn-secondary p-3 fs-4">로그인하기</button>
          </form>
          <div className='d-flex align-items-center justify-content-center mt-3'>
            <Link className='text-decoration-none' to="/signUp"><p className='text-secondary fs-6 m-0'>회원가입</p></Link>
            <span style={{width:'0.05em', height:'2em'}} className='mx-3 bg-secondary'></span>
            <p className='text-secondary fs-6 m-0'>비밀번호 찾기</p> 
          </div>
        </div>
      </section>
    </>
  )
}