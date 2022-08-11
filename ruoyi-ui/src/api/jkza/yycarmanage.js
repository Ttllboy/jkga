import request from '@/utils/request'

// 查询医院车辆列表
export function listYycarmanage(query) {
  return request({
    url: '/jkza/yycarmanage/list',
    method: 'get',
    params: query
  })
}

// 查询医院车辆详细
export function getYycarmanage(id) {
  return request({
    url: '/jkza/yycarmanage/' + id,
    method: 'get'
  })
}

// 新增医院车辆
export function addYycarmanage(data) {
  return request({
    url: '/jkza/yycarmanage',
    method: 'post',
    data: data
  })
}

// 修改医院车辆
export function updateYycarmanage(data) {
  return request({
    url: '/jkza/yycarmanage',
    method: 'put',
    data: data
  })
}

// 删除医院车辆
export function delYycarmanage(id) {
  return request({
    url: '/jkza/yycarmanage/' + id,
    method: 'delete'
  })
}

//获取所有医院名称
export function listHosNames() {
  return request({
    url: '/jkza/yycarmanage/listHosNames',
    method: 'post',
  })
}
