import classes from './MyPage.module.css'
import { CARD_BOX } from '../util/CardBoxData'
import CardBox from '../component/CardBox'
import { loginCheck } from '../util/LoginCheck'
import { redirect, useLoaderData, useNavigate } from 'react-router-dom'

export default function MyPage()
{
    let name = localStorage.getItem("name")
    let major = localStorage.getItem("major")
    let admissionYear = localStorage.getItem("admissionYear")
    let progress = useLoaderData()
    const navigate = useNavigate()
    function navigationHandler(link)
    {
        navigate("/" + link)
    }
    return(
        <div className="flex-grow-1 d-flex align-items-center justify-content-center">
            <section className="d-flex align-items-center justify-content-center py-4">
                <div>
                    <h1 className="fs-3 fw-bold m-0 mb-2">안녕하세요,{name}님!</h1>
                    <p className="text-secondar mb-4">{major}•입학년도: {admissionYear}</p>
                    <div className={`d-grid gap-0 row-gap-4 border border-secondary-subtle bg-white rounded-4 shadow-sm py-3 mb-5 ${classes.box}`}>
                        <div className='px-4 d-flex column-gap-2 align-items-center mt-2'>
                            <svg style={{color:"#AD46FF"}} xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-bar-chart-fill" viewBox="0 0 16 16">
                                <path d="M1 11a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1zm5-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1z"/>
                            </svg>
                            <span className='fw-semibold'>졸업 진행률</span>
                        </div>
                        <div onClick={() => navigationHandler("graduation-check")} className='px-4'>
                            <div className='row align-items-center'>
                                <div className='col'>
                                    <div className="progress" role="progressbar" aria-label="Basic example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                        <div className={`progress-bar ${classes.progress_bar}`} style={{width:`${progress}%`}}></div>
                                    </div>
                                </div>
                                <div style={{color:"#AD46FF"}} className='col-auto fs-3 fw-bold'>
                                    {progress}%
                                </div>
                            </div>
                            <p className='m-0 text-secondary'>졸업까지 {100 - progress}%남았습니다.</p>
                        </div>
                    </div>
                    <div className='row g-3 row-cols-1 row-cols-md-2 row-cols-lg-4'>
                        {CARD_BOX.map((value, index)=>
                        <CardBox index={index} svg={value.icon} tilte={value.title} content={value.content} link={value.link}></CardBox>      
                        )}
                    </div>
                </div>
            </section>
        </div>
    )
}

export async function loader({request, param}) {
    let returnValue = await loginCheck()
    if(returnValue)
    {
        try
        {
            const response = await fetch("http://localhost:8080/user/totalProgress", {
                credentials: 'include'
            });    
            if(response.ok)
            {
                const num = await response.json();
                console.log(num)           
                return num
            }
            else
            {
                return 0;
            }
        }
        catch
        {
            return 0;
        }
    }
    else
    {
        throw redirect("/login")
    }
}
