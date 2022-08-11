import request from '@/utils/request'

// 查询车辆卡口列表
export function listYyCar(query) {
  return request({
    url: '/jkza/yyCar/list',
    method: 'get',
    params: query
  })
}

// 查询车辆卡口详细
export function getYyCar(id) {
  return request({
    url: '/jkza/yyCar/' + id,
    method: 'get'
  })
}

// 新增车辆卡口
export function addYyCar(data) {
  return request({
    url: '/jkza/yyCar',
    method: 'post',
    data: data
  })
}

// 修改车辆卡口
export function updateYyCar(data) {
  return request({
    url: '/jkza/yyCar',
    method: 'put',
    data: data
  })
}

// 删除车辆卡口
export function delYyCar(id) {
  return request({
    url: '/jkza/yyCar/' + id,
    method: 'delete'
  })
}
