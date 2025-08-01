import { useRef, useState } from 'react'
import classes from './SignUp.module.css'
import { data } from 'react-router-dom';

export default function SignUp() {
    
    const [mismatch, setMismatch] = useState(false);
    const [idDuplicate , setIdDuplicate] = useState(null);
    const modalRef = useRef(null)
    const id = useRef(null)
    const name = useRef(null)
    const admissionYear = useRef(null)
    const major = useRef(null)
    const password = useRef(null)
    const confirmPassword = useRef(null)
    function openModal()
    {
        const modal = new bootstrap.Modal(modalRef.current);
        modal.show();
    }

    function checkPassword()
    {
        if(password.current.value !== confirmPassword.current.value)
        {
            setMismatch(true)
        }
        else
        {
            setMismatch(false)
        }
    }

    async function duplicateCheck()
    {
        try 
        {
            const response = await fetch(`http://localhost:8080/register/checkDuplicate?id=${id.current.value}`, {
                method: 'POST',
                credentials: 'include'
            });
            const data = response.text()
            if (!response.ok) 
            {
                setIdDuplicate(                        
                    <div className='text-danger d-flex align-items-center column-gap-2'>
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" className="bi bi-exclamation-circle text-danger" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                            <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                        </svg>
                        {data}
                    </div> );
            }
            else
            {
                setIdDuplicate(                        
                    <div className='text-success d-flex align-items-center column-gap-2'>
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-check-circle text-success" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                            <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
                        </svg>
                        {data}
                    </div> );
            }
        } catch (error) {
            console.error('에러:', error);
        }
    }

    async function send(event) 
    {
        event.preventDefault()
        let data = {
            "userID" : id.current.value,
            "name" : name.current.value,
            "password" : password.current.value,
            "admissionYear" : admissionYear.current.value,
            "major" : major.current.value,
        }
        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                credentials: 'include',
                headers : {
                    "Content-Type": "application/json"
                },
                body : JSON.stringify(data),
            });
            const body = await response.json()
            if (!response.ok) 
            {
                console.log(response);
                console.log(body);
                throw new Error('등록 실패');
            }
            else
            {

            }
        } catch (error) 
        {
            console.error('에러:', error);
        }
    }

  return (
    <>
        <section className='d-flex align-items-center justify-content-center bg-body-tertiary'>
            <div className='my-5'>
                <p className='fw-bold fs-4'>회원가입</p>
                <hr className='mb-4' />
                <form onSubmit={(event) => send(event)}>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >이름</p>
                        <input ref={name} className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="이름을 입력해주세요"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >입학년도</p>
                        <input ref={admissionYear} className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="YYYY"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >학과</p>
                        <input ref={major} onClick={openModal} className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="학과를 검색해주세요"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >아이디</p>
                        <input ref={id} className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="아이디를 입력하세요"/>
                        <button type='button' className={`w-100 border-0 rounded-3 p-3 fs-6 fw-bold text-primary ${classes.button}`} onClick={duplicateCheck}>중복확인</button>
                        {idDuplicate}
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >비밀번호</p>
                        <input ref={password} className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="password" placeholder="아이디를 입력하세요"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >비밀번호 확인</p>
                        <input ref={confirmPassword} onBlur={checkPassword} className={`form-control border ${mismatch ? "border-danger" : "border-secondary-subtle"} p-3 rounded-3 ${classes.input}`} type="password" placeholder="아이디를 입력하세요"/>
                        {mismatch && 
                        <div className='text-danger d-flex align-items-center column-gap-2'>
                            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" className="bi bi-exclamation-circle text-danger" viewBox="0 0 16 16">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                                <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                            </svg>
                            비밀번호가 일치하지 않습니다.
                        </div> 
                        }
                    </div>
                    <button type="submit" className="btn btn-secondary w-100 py-3 fs-5 fw-bold">완료</button>
                </form>
            </div>
            <div ref={modalRef} className="modal" tabindex="-1">
                <div className="modal-dialog modal-dialog-centered justify-content-center">
                    <div style={{width:'26em'}} className="modal-content">
                        <div className="modal-header border-bottom-0 pb-0">
                            <p className='fw-bold fs-6'>학과 검색</p>
                        </div>
                        <div style={{height:'27em'}} className="modal-body pt-0" >
                            <div className='d-flex column-gap-3'>
                                <input className={`form-control border border-secondary-subtle p-3 rounded-3 flex-grow-1 ${classes.modalInput}`} type="text" placeholder="학과명을 입력해주세요"/>
                                <button type="button" className={`border-0 rounded-3 px-3 fw-bold text-primary ${classes.modalButton}`}>검색</button>
                            </div>
                        </div>
                        <div className="modal-footer d-flex justify-content-center column-gap-3">
                            <div className='flex-fill'>
                                <button type="button" className="btn btn-secondary w-100 fw-medium py-2" data-bs-dismiss="modal">닫기</button>
                            </div>
                            <div className='flex-fill'>
                                <button type="button" className="btn btn-primary w-100 fw-medium py-2">확인</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </>
  )
}