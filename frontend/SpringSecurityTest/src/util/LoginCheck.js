export async function loginCheck()
{
    const response = await fetch("http://localhost:8080/test", {
        credentials: 'include'
    });    
    if(response.ok)
    {
        return true;
    }
    else
    {
        return false;
    }
}