import request from '@/utils/request'

// 查询管理人员考勤列表
export function listManagerCheck(query) {
  return request({
    url: '/jkza/managerCheck/list',
    method: 'get',
    params: query
  })
}

// 查询管理人员考勤详细
export function getManagerCheck(id) {
  return request({
    url: '/jkza/managerCheck/' + id,
    method: 'get'
  })
}

// 新增管理人员考勤
export function addManagerCheck(data) {
  return request({
    url: '/jkza/managerCheck',
    method: 'post',
    data: data
  })
}

// 修改管理人员考勤
export function updateManagerCheck(data) {
  return request({
    url: '/jkza/managerCheck',
    method: 'put',
    data: data
  })
}

// 删除管理人员考勤
export function delManagerCheck(id) {
  return request({
    url: '/jkza/managerCheck/' + id,
    method: 'delete'
  })
}
