export default function Donut({ value = 81, color = 'var(--bs-danger)', size = 96 })
{
  const ring = {
    width: size, height: size, borderRadius: '50%',
    background: `conic-gradient(${color} ${value}%, #eee 0)`
  };
  const hole = {
    width: size*0.7, height: size*0.7
  };
  return (
    <div className="position-relative d-inline-grid" style={{placeItems:'center', ...ring}}>
      <div className="bg-white rounded-circle" style={hole}></div>
      <span className="position-absolute fw-bold" style={{color}}>{value}%</span>
    </div>
  );
}