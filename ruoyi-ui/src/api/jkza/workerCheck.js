import request from '@/utils/request'

// 查询考勤信息列表
export function listWorkerCheck(query) {
  return request({
    url: '/jkza/workerCheck/list',
    method: 'get',
    params: query
  })
}

// 查询考勤信息详细
export function getWorkerCheck(id) {
  return request({
    url: '/jkza/workerCheck/' + id,
    method: 'get'
  })
}

// 新增考勤信息
export function addWorkerCheck(data) {
  return request({
    url: '/jkza/workerCheck',
    method: 'post',
    data: data
  })
}

// 修改考勤信息
export function updateWorkerCheck(data) {
  return request({
    url: '/jkza/workerCheck',
    method: 'put',
    data: data
  })
}

// 删除考勤信息
export function delWorkerCheck(id) {
  return request({
    url: '/jkza/workerCheck/' + id,
    method: 'delete'
  })
}
