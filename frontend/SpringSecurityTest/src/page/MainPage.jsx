import { useNavigate } from 'react-router-dom'
import classes from './MainPage.module.css'


export default function MainPage()
{
    const navigate = useNavigate()
    const isAuthentication = useLoaderData();
    function navigationHandler(link)
    {
        navigate("/" + link)
    }
    return(
        <div className="flex-grow-1">
            <div className='d-flex justify-content-center align-items-center h-100'>
                <center>
                    <div className={`d-flex align-items-center justify-content-center bg-gradient-custom rounded-4 ${classes.gradient} `} style={{width: "4.0rem", height: "4.0rem"}}>
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" className="bi bi-mortarboard text-white" viewBox="0 0 16 16">
                            <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917zM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
                            <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46z"/>
                        </svg>
                    </div>
                    <p className='mt-3 fs-1 fw-bold mb-2'>복잡한 졸업관리를 쉽게, G-Check</p>
                    <p className='text-black-50 fs-5 mb-5'>대학교 졸업 요건부터 학점 관리까지, 모든 것을 한 곳에서 간편하게 관리하세요</p>
                    <div className="d-md-flex align-items-center justify-content-center gap-3 xl">
                        {
                            isAuthentication ? 
                                <>
                                    <div>
                                        <button onClick={() => navigationHandler("login")} type="button" style={{"--bs-btn-border-color": "#e5e5e5","--bs-btn-hover-border-color": "#e5e5e5"}} className="btn btn-light fw-bolder px-3">마이페이지</button>
                                    </div>
                                </>
                            : 
                                <>
                                    <div className='mb-3 mb-md-0'>
                                        <button onClick={() => navigationHandler("signUp")} type="button" style={{"--bs-btn-bg": "#AD46FF","--bs-btn-border-color": "#AD46FF","--bs-btn-hover-bg": "#922fe0","--bs-btn-hover-border-color": "#922fe0", "--bs-btn-active-bg" : "#922fe0", "--bs-btn-active-border-color" : "#922fe0"}} className="btn btn-success fw-bolder px-3f">지금 시작하기</button>
                                    </div>
                                    <div>
                                        <button onClick={() => navigationHandler("login")} type="button" style={{"--bs-btn-border-color": "#e5e5e5","--bs-btn-hover-border-color": "#e5e5e5"}} className="btn btn-light fw-bolder px-3">로그인하기</button>
                                    </div>
                                </>
                        }
                    </div>
                </center>
            </div>
        </div>
    )
}