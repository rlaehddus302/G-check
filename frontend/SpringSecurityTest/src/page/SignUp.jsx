import { useRef } from 'react'
import classes from './SignUp.module.css'

export default function SignUp() {
    
    const modalRef = useRef(null)

    function openModal()
    {
        const modal = new bootstrap.Modal(modalRef.current);
        modal.show();
    }

    async function duplicateCheck()
    {

    }

    async function send(event) 
    {
        
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
                        <input className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="이름을 입력해주세요"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >입학년도</p>
                        <input className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="YYYY"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >학과</p>
                        <input onClick={openModal} className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="학과를 검색해주세요"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >아이디</p>
                        <input className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="아이디를 입력하세요"/>
                        <button type='button' className={`w-100 border-0 rounded-3 p-3 fs-6 fw-bold text-primary ${classes.button}`} onClick={duplicateCheck}>중복확인</button>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >비밀번호</p>
                        <input className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="아이디를 입력하세요"/>
                    </div>
                    <div className='mb-5'>
                        <p style={{fontSize:'1.1em'}} className='fw-bold mb-2' >비밀번호 확인</p>
                        <input className={`form-control border border-secondary-subtle p-3 rounded-3 ${classes.input}`} type="text" placeholder="아이디를 입력하세요"/>
                    </div>
                    <button type="button" class="btn btn-secondary w-100 py-3 fs-5 fw-bold">완료</button>
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