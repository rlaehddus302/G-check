import { useRef, useState } from 'react'
import classes from './Login.module.css'
export default function Login() {

  const id = useRef()
  const password = useRef()

  async function login(event) 
  {
    event.preventDefault()
    const credentials = window.btoa(`${id.current.value}:${password.current.value}`); 
    try {
      const response = await fetch('http://localhost:8080/loginIn', {
        method: 'POST',
        headers: {
          'Authorization': `Basic ${credentials}`,
          'Content-Type': 'application/json'
        },
        credentials: 'include'
      });
        data = await response.json()
      if (!response.ok) {
        console.log(response);
        throw new Error('로그인 실패');
      }
    } catch (error) {
      console.error('에러:', error);
    }
  }

  return (
    <>
      <section className='d-flex align-items-center justify-content-center vw-100 vh-100 bg-body-tertiary'>
        <div>
          <p className='d-flex align-items-center justify-content-center'>
            <img className={classes.img} src="/vite.svg" alt="not found"/>
            <span className={classes.title}>G-Check</span>
          </p>
          <p className={classes.paragraph}>복잡한 졸업관리를 쉽게, G-check</p>
          <form onSubmit={login}>
            <input ref={id} type="text" id='id' className={`border border-secondary-subtle p-3 rounded-3 ${classes.input}`} placeholder="아이디"/>
            <input ref={password} type="password" id='password' className={`border border-secondary-subtle p-3 rounded-3 ${classes.input}`} placeholder='비밀번호' />
            <button className="w-100 btn btn-secondary p-3 fs-4">로그인하기</button>
          </form>
          <div className='d-flex align-items-center justify-content-center mt-3'>
            <p className='text-secondary fs-6 m-0'>회원가입</p>
            <span style={{width:'0.05em', height:'2em'}} className='mx-3 bg-secondary'></span>
            <p className='text-secondary fs-6 m-0'>비밀번호 찾기</p>
          </div>
        </div>
      </section>
    </>
  )
}